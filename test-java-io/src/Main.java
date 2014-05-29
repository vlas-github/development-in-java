import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class Main {
	
	public static void main(String [] arg) {
		
		byte [] b1 = {-1, 0, 1};
		
		ByteArrayInputStream byteArrayInput = null;
		
		try {
			
			byteArrayInput = new ByteArrayInputStream(b1);
			
			int i1 = byteArrayInput.read();
			System.out.println(i1);
			
			int i2 = byteArrayInput.read();
			System.out.println(i2);
			
			int i3 = byteArrayInput.read();
			System.out.println(i3);
		
		} finally {
		
			try {
			
				byteArrayInput.close();
			
			} catch (IOException e) {
				
				System.err.println(e.getMessage());
			}
		}
		
		//1. Создать массив байт и проинициализировать его
		byte [] bytes = {1, 5, 123, 12, 54, 76, 12, 43, 12, 45, 78, 12};
		
		//2. Создать выходной поток FileOutputStream
		
		OutputStream out = null;
		
		try {
			
			out = new FileOutputStream("file");
			
			//3. Заполнить выходной поток массивом байт за одну операцию
			out.write(bytes);
			
			//4. Вывести на консоль сообщение о том сколько реально записанно в поток
			System.out.println();
			
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
			
		} catch(IOException e) {
			
			System.err.println(e.getMessage());
			
		} finally {
			
			//5. close()
			try {
				
				out.close();
			
			} catch (IOException e) {
				
				System.err.println(e.getMessage());
			}
		}
		

		//6. Создать FileInputStream
		InputStream in = null;
		
		try {
			
			in = new FileInputStream("file");	
			
			//7. Узанть сколько байт готово к считыванию и вывести на консоль
			int i = in.available();		
			
			//8. Создать массив байт для чтения данных
			byte [] b = new byte [i];
			
			//9. Считать все данные
			int count = in.read(b);
			
			//10. Вывести на консоль сообщение сколько байт реально считано
			System.out.println(count);
			
		} catch (FileNotFoundException e) {
			
			System.err.println(e.getMessage());
			
		} catch(IOException e) {
			
			System.err.println(e.getMessage());
			
		} finally {
			
			//11. close()
			try {
				
				in.close();
			
			} catch (IOException e) {
				
				System.err.println(e.getMessage());
			}
		}		
	}
}
