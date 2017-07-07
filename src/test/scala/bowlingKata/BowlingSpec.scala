package bowlingKata

import org.scalatest.{FlatSpec, Matchers}


class BowlingSpec extends FlatSpec with Matchers {

  behavior of "FrameNumber"

  it should "have a next method" in {
    FrameNumber(1).next shouldBe FrameNumber(2)
  }


  behavior of "SimpleFrame"
  it should "score" in {
    SimpleFrame(FrameNumber(1), 3, 4).score shouldBe 7
  }

  behavior of "Spare"
  it should "score" in {
    Spare(FrameNumber(1), 3, 7, 5).score shouldBe 15
  }
  behavior of "Strike"
  it should "score" in {
    Strike(FrameNumber(1), 3, 2).score shouldBe 15
  }
  behavior of "FrameData"

  val fn3 = FrameNumber(3)
  val fn4 = FrameNumber(4)

  def makeFrameData3(ints: Int*) = FrameData(ints.toList, fn3)

  def makeFrameData4(ints: Int*) = FrameData(ints.toList, fn4)

  it should "have a 'useUp' method that increments the frame number and eats up 'n' balls" in {
    val fd = makeFrameData3(1, 2, 3, 4, 5)
    fd.useUp(1) shouldBe makeFrameData4(2, 3, 4, 5)
    fd.useUp(2) shouldBe makeFrameData4(3, 4, 5)
  }

  behavior of "FrameData.unfoldIntoFrames function"

  def applyUnfold(ints: Int*): Option[(Frame, FrameData)] = FrameData.unfoldIntoFrames(makeFrameData3(ints: _*))

  it should "detect simple frames" in {
    applyUnfold(1, 2, 3, 4) shouldBe Some((SimpleFrame(fn3, 1, 2), makeFrameData4(3, 4)))
  }
  it should "detect spare frames" in {
    applyUnfold(7, 3, 5, 4) shouldBe Some((Spare(fn3, 7, 3, 5), makeFrameData4(5, 4)))
  }
  it should "detect strike frames" in {
    applyUnfold(10, 3, 5, 4) shouldBe Some((Strike(fn3, 3, 5), makeFrameData4(3, 5, 4)))
  }

  behavior of "FrameData.makeFrames"
  it should "turn ints into a list of frames" in {
    val stream = FrameData.makeFrames(FrameData(List(7, 3, 1, 2), FrameNumber(0)))
    stream.head shouldBe Spare(FrameNumber(0), 7, 3, 1)
  }
  behavior of "FrameData.Score"
  it should "calculate the score" in {
    FrameData.score(List(1, 2)) shouldBe 3
    FrameData.score(List(7, 3, 1, 2)) shouldBe 11 + 3
    FrameData.score(List(10, 3, 1, 2, 4)) shouldBe 14 + 4 + 6
    FrameData.score(List(7, 2, 5, 5, 3, 0, 10, 2, 4)) shouldBe 9 + 13 + 3 + 16 + 6
    FrameData.score(List.fill(12)(10)) shouldBe 300 //maxScore
  }
}
