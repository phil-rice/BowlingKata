package bowlingKata

class IllegalFrameNumberException(val num: Int) extends Exception(s"Frame Number($num) is more than 10")

case class FrameNumber(val num: Int)  {
  if (num > 10) throw new IllegalFrameNumberException(num)

  def next: FrameNumber = FrameNumber(num + 1)
}

trait Frame {
  def frameNumber: FrameNumber

  def score: Int
}

case class SimpleFrame(frameNumber: FrameNumber, first: Int, second: Int) extends Frame {
  override def score: Int = first + second
}

case class Strike(frameNumber: FrameNumber, bonus1: Int, bonus2: Int) extends Frame {
  override def score: Int = 10+ bonus1 + bonus2
}

case class Spare(frameNumber: FrameNumber, first: Int, second: Int, bonus: Int) extends Frame {
  override def score: Int = first + second + bonus
}
