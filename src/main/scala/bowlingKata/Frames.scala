package bowlingKata

case class FrameNumber(val num: Int) {
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
  override def score: Int = 10 + bonus1 + bonus2
}

case class Spare(frameNumber: FrameNumber, first: Int, second: Int, bonus: Int) extends Frame {
  override def score: Int = first + second + bonus
}
