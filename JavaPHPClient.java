import java.io.*; 
import java.net.*; 

/** 
 * 
 * @author Jeet Shah
 */ 

public class JavaPHPClient { 
	public static void main(String[] args) throws IOException { 

		try { 
			//Read the line form the command prompt 
			String filePath,fileName; 
			
			BufferedReader in = new BufferedReader(new 
			InputStreamReader(System.in)); 

			do { 
				System.out.println("File Path: "); 
				
				//Reads the File path to upload 
				filePath = in.readLine(); 

				if(filePath!=null) { 
					//Full path string 
					filePath=filePath.replaceAll("\\\\","/"); 

					//Exact file name 
					fileName=filePath.substring(filePath.lastIndexOf("/")+1
				); 

				File file =new File(filePath); 
				
				//Validation 
				if (file.exists()) { 

					String url="http://jeetshah.com/PHPJavaServer.php?fileName="+fileName; 
					URL serverURL = new URL(url); 
		
					//Make the HTTP connection 
					HttpURLConnection conn = (HttpURLConnection) 
					serverURL.openConnection(); 

					// Sets the connection to for Output 
					conn.setDoOutput(true); 
					conn.setDoInput(true); 

					// Sets the request method to Post 
					conn.setRequestMethod("POST"); 
					
					//File Length 
					int fileLength=(int)file.length(); 

					FileInputStream fis =new FileInputStream(file); 
					PrintStream wr = new 
					PrintStream(conn.getOutputStream(), true); 
			
					for(int i=0;i<fileLength;i++) { 
						wr.write(fis.read()); 
					} 

					wr.close(); 
					BufferedReader bget=new BufferedReader(new 
					InputStreamReader(conn.getInputStream()));//To read response 
				
					String s = null; 
					
					while ((s = bget.readLine()) != null) { 
						System.out.println(s); 
					} 
					
					bget.close(); 
					fis.close(); 
				} 
				else { 
					System.out.println("Enter Valid Path: "); 
				} 
				} 
				} 
	
			while(!filePath.equalsIgnoreCase("QUIT")); 
			} 
		catch (Exception err) { 
			System.err.println(err); 
		} 
	} 
}
