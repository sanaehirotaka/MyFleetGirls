package com.ponkotuy.restype

import com.ponkotuy.parser.Query
import com.ponkotuy.data

import scala.util.matching.Regex

object PresetSelect extends ResType {
  import ResType._

  override def regexp: Regex = s"\\A${ReqHensei}/preset_select".r

  override def postables(q: Query): Seq[Result] = {
    data.PresetSelect.fromJson(q.obj).foreach { preset =>
      FleetsState.getFleet(preset.id).foreach(_.replace(preset.ship))
    }
    Nil
  }
}
