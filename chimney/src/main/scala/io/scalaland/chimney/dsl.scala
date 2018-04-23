package io.scalaland.chimney

import io.scalaland.chimney.internal.{ChimneyBlackboxMacros, ChimneyWhiteboxMacros, DisableDefaults, Empty, Cfg}

import scala.language.experimental.macros

object dsl {

  implicit class TransformerOps[From](val source: From) extends AnyVal {

    final def into[To]: TransformerInto[From, To, Empty] =
      new TransformerInto[From, To, Empty](source, Map.empty, Map.empty)

    final def transformInto[To](implicit transformer: Transformer[From, To]): To =
      transformer.transform(source)
  }

  final class TransformerInto[From, To, C <: Cfg](val source: From,
                                                  val overrides: Map[String, Any],
                                                  val instances: Map[(String, String), Any]) {

    def disableDefaultValues: TransformerInto[From, To, DisableDefaults[C]] =
      new TransformerInto[From, To, DisableDefaults[C]](source, overrides, instances)

    def withFieldConst[T](selector: To => T, value: T): TransformerInto[From, To, _] =
      macro ChimneyWhiteboxMacros.withFieldConstImpl[From, To, T, C]

    def withFieldComputed[T](selector: To => T, map: From => T): TransformerInto[From, To, _] =
      macro ChimneyWhiteboxMacros.withFieldComputedImpl[From, To, T, C]

    def withFieldRenamed[T](selectorFrom: From => T, selectorTo: To => T): TransformerInto[From, To, _] =
      macro ChimneyWhiteboxMacros.withFieldRenamedImpl[From, To, T, C]

    def withCoproductInstance[Inst](f: Inst => To): TransformerInto[From, To, _] =
      macro ChimneyWhiteboxMacros.withCoproductInstanceImpl[From, To, Inst, C]

    def transform: To =
      macro ChimneyBlackboxMacros.transformImpl[From, To, C]
  }

  implicit class PatcherOps[T](val obj: T) extends AnyVal {

    final def patchWith[P](patch: P)(implicit patcher: Patcher[T, P]): T =
      patcher.patch(obj, patch)
  }
}
