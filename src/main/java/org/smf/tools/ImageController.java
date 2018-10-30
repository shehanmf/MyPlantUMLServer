package org.smf.tools;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author shehan.fernando
 */
@RestController
public class ImageController
{

  @RequestMapping("/uml")
  public String index()
  {
    return "printing UML";
  }

  @ResponseBody
  @GetMapping(
    value = "/photo2",
    produces = MediaType.IMAGE_JPEG_VALUE
  )
  public byte[] getImageWithMediaType() throws IOException
  {
    File  file  = new File("C:/Personal/selected.png");
    InputStream in = new FileInputStream(file);
    return IOUtils.toByteArray(in);
  }
}
