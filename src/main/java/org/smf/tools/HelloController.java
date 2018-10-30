package org.smf.tools;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shehan.fernando
 */
@RestController
public class HelloController
{

  @RequestMapping("/")
  public String index()
  {
    return "Greetings from Spring Boot!";
  }

}
