package models

object Type extends Enumeration {
  type Type = Value
  val Class, Field, Method = Value
}

object Side extends Enumeration {
  type Side = Value
  val Server, Client, Both = Value
}

import models.Type._
import models.Side._
import java.text.SimpleDateFormat
import java.util.Date

case class NameItem(id: Int, notchName: String, srgName: String, deobfuscatedName: String, ntype: Type, side: Side)

object NameItem {
  private val dateFormatter = new SimpleDateFormat("k:mm:ss d. M. yyyy")

  private def lcc(whole: String, partInLowerCase: String) = whole.toLowerCase.contains(partInLowerCase)

  def searchPartials(q: String, max: Int): List[NameItem] = {
    if (q.isEmpty) all()

    var c = 0
    for {
      i <- all()
      lq = q.toLowerCase
      if c < max
      if lcc(i.notchName, lq) || lcc(i.srgName, lq) || lcc(i.deobfuscatedName, lq)
    } yield {
      c += 1
      i
    }
  }

  def all(): List[NameItem] = testList._1

  def testListCreatedOn() = testList._2

  var testList = createTestList

  private def createTestList = {
    (
      List[NameItem](
        NameItem(0, "notch_0", "srg_0", "deobf_0", Method, Server),
        NameItem(1, "notch_1", "srg_1", "deobf_1", Field, Client),
        NameItem(2, "aba", "net/minecraft/src/EnchantmentModifierDamage", "EnchantmentModifierDamage", Class, Client),
        NameItem(3, "anz/h", "net/minecraft/src/BlockDoor/func_71915_e", "getMobilityFlag", Method, Both)
      ) ::: (for (i <- 4 to 20) yield NameItem(i, "notch_" + i, "srg_" + i, "deobf_" + i, Method, Both)).toList,
      dateFormatter.format(new Date())
      )
  }

  def create(notchName: String, srgName: String, deobfuscatedName: String, ntype: Type, side: Side) {}

  def delete(id: Int) {}
}
