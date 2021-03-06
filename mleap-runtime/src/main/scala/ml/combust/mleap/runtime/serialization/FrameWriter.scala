package ml.combust.mleap.runtime.serialization

import java.io.{File, FileOutputStream}
import java.nio.charset.Charset

import ml.combust.bundle.util.ClassLoaderUtil
import ml.combust.mleap.runtime.LeapFrame
import resource._

/**
  * Created by hollinwilkins on 11/1/16.
  */
object FrameWriter {
  def apply(format: String = BuiltinFormats.json,
            clOption: Option[ClassLoader] = None): FrameWriter = {
    val cl = clOption.getOrElse(ClassLoaderUtil.findClassLoader(classOf[FrameWriter].getCanonicalName))
    cl.loadClass(s"$format.DefaultFrameWriter").
      newInstance().
      asInstanceOf[FrameWriter]
  }
}

trait FrameWriter {
  def toBytes[LF <: LeapFrame[LF]](frame: LF, charset: Charset = BuiltinFormats.charset): Array[Byte]

  def write[LF <: LeapFrame[LF]](frame: LF, file: File, charset: Charset = BuiltinFormats.charset): Unit = {
    val bytes = toBytes(frame)
    for(out <- managed(new FileOutputStream(file))) {
      out.write(bytes)
    }
  }
}
