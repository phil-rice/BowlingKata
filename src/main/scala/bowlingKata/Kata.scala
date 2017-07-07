package bowlingKata

object Unfolder {
  def unfold[S, A](z: S)(f: S => Option[(A, S)]): Stream[A] = f(z) match {
    case Some((a, s)) => a #:: unfold(s)(f)
    case None => Stream.empty[A]
  }
}

object FrameData {

  def score(list: List[Int]) = makeFrames(FrameData(list, FrameNumber(0))).toList.map(_.score).sum

  def makeFrames(fd: FrameData): Stream[Frame] = {
    Unfolder.unfold[FrameData, Frame](fd)(FrameData.unfoldIntoFrames)
  }

  def unfoldIntoFrames(fd: FrameData): Option[(Frame, FrameData)] = fd match {
    case FrameData(Nil, _) => None
    case FrameData(_, FrameNumber(count)) if count > 10 => None
    case FrameData(a :: Nil, count) => throw new IllegalStateException("Cannot occur in a legal match: single ball left")
    case fd@FrameData(10 :: bonus1 :: bonus2 :: _, frameNumber) => Some((Strike(frameNumber, bonus1, bonus2), fd.useUp(1)))
    case fd@FrameData(first :: second :: bonus :: _, frameNumber) if (first + second == 10) => Some((Spare(frameNumber, first, second, bonus), fd.useUp(2)))
    case fd@FrameData(first :: second :: _, frameNumber) => Some((SimpleFrame(frameNumber, first, second), fd.useUp(2)))
    case a => throw new IllegalStateException(s"Not sure what happened. Funny state: $a")
  }
}

case class FrameData(pinsFromNowOn: List[Int], frameNumber: FrameNumber) {
  def useUp(n: Int) = FrameData(pinsFromNowOn.drop(n), frameNumber.next)
}

object Kata extends App {

  def game(list: List[Int]) = {
    val frames = FrameData.makeFrames(FrameData(list, new FrameNumber(0))).toList
    println(frames)
    println("score is " + frames.map(_.score).sum)
  }

  game(List(1, 2, 6, 4, 10, 5, 6, 7, 3))
  game(List(10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10))
}
