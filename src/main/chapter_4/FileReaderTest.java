package main.chapter_4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FileReaderTest {									//a JUnit case
	@BeforeClass												//整个类的测试环境，执行一次
	public static void once() throws Exception {
		File dataFile = new File(DATA_FILE_PATH);
		if( !dataFile.exists() )
			dataFile.createNewFile();
		FileWriter out = new FileWriter(dataFile);
		out.write(TEST_TEXT);
		out.flush();
		out.close();
		
		File emptyFile = new File(EMPTY_FILE_PATH);
		if( emptyFile.exists() ) {
			FileWriter out2 = new FileWriter(emptyFile);
			out2.write("");
			out2.flush();
			out2.close();
		}else
			emptyFile.createNewFile();
	}
	@Before														//每个方法执行前，初始化
	public void before() throws Exception {
		_inData = new FileReader(new File(DATA_FILE_PATH));
		_inEmpty = new FileReader(new File(EMPTY_FILE_PATH));
	}
	@After														//每个方法执行完，清理工作
	public void after() throws Exception {
		_inData.close();
		_inEmpty.close();
	}

	public static final String DATA_FILE_PATH = "data.txt";
	public static final String EMPTY_FILE_PATH = "empty.txt";
	public static final String TEST_TEXT = "12345abcde";
	private FileReader _inData;
	private FileReader _inEmpty;

	@Test
	public void testRead() throws IOException {
		for(int i=0; i<TEST_TEXT.length(); i++) {
			int c = _inData.read();
			if(i==0)
				assertEquals('1', (char)c);
			if(i==5)
				assertEquals('a', (char)c);
			if(i==9)
				assertEquals('e', (char)c);
		}
		assertEquals(-1, _inData.read());
	}
	
	@Test
	public void testReadAtEnd() throws Exception {
		for(int i=0; i<TEST_TEXT.length(); i++) 
			_inData.read();
		assertEquals(-1, _inData.read());
	}
	
	//边界测试
	@Test
	public void testReadBoundaries() throws Exception {
		assertEquals('1', _inData.read());
		for(int i=1; i<TEST_TEXT.length()-1; i++) {
			_inData.read();
		}
		assertEquals('e', _inData.read());
		assertEquals(-1, _inData.read());
		assertEquals(-1, _inData.read());
	}
	
	//特例测试（例如：空文件）
	@Test
	public void testEmptyRead() throws Exception {
		assertEquals(-1, _inEmpty.read());
	}
	
	//异常测试
	@Test
	public void testReadAfterClose() throws Exception {
		_inData.close();
		try {
			_inData.read();
			fail("在文件流关闭后 读文件，却没有报错");
		} catch(IOException e) { }
	}

}
