package main.chapter_4;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ FileReaderTest.class })						//用来加载、运行多个case
public class AllTests {										//a JUnit suite

}
