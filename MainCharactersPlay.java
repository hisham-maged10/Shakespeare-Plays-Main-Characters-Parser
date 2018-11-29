/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.util.Collections;
public class MainCharactersPlay
{
	private ArrayList<String> mainCharacters=new ArrayList<>();
	private ArrayList<Integer> appearance=new ArrayList<>();
	private String[] lines;
	private int avgSntncLen;
	public MainCharactersPlay()
	{
		this.lines=fillLines(getFile());
		this.avgSntncLen=getAverageSentenceLength();
		fillMainCharacters();
	}
	public String[] getMaxCharacter()
	{
		int index=appearance.indexOf(Collections.max(appearance));
		String[] maxInfo=new String[2];
		maxInfo[0]=mainCharacters.get(index);
		maxInfo[1]=Integer.toString(appearance.get(index));
		return maxInfo;
	}
	public String[] getCharactersWithNumParts(int num1,int num2)
	{
		ArrayList<String> characters= new ArrayList<>();
		for(int i=0,n=mainCharacters.size();i<n;i++)
		{
			if(appearance.get(i)>=num1 && appearance.get(i)<=num2)
			characters.add(mainCharacters.get(i));
		}
		return characters.toArray(new String[characters.size()]);
	}
	public String[] getMainCharacters()
	{
		return this.mainCharacters.toArray(new String[mainCharacters.size()]);
	}
	public Integer[] getAppearance()
	{
		return this.appearance.toArray(new Integer[appearance.size()]);
	}
	private String[] fillLines(File source)
	{
		ArrayList<String> lines=new ArrayList<String>();
		try(Scanner input=new Scanner(source))
		{
			while(input.hasNextLine())
				lines.add(input.nextLine());
	
		}catch(IOException ex){ex.printStackTrace();}
		return lines.toArray(new String[lines.size()]);
	}
	private int getAverageSentenceLength()
	{
		int tallest=0;
		for(String e:lines)
		{
			if(tallest<e.trim().length())tallest=e.trim().length();
		}
		return (int)(tallest/2.0);
	}
	private  File getFile()
	{
		JFileChooser chooser=new JFileChooser();
		chooser.setCurrentDirectory(new File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		try{
		do
		{
			System.out.println("Please choose a file");
			chooser.showOpenDialog(null);
		}while(chooser.getSelectedFile() == null);
		}catch(NullPointerException ex){System.out.println("Incorrect Response!"); return getFile();}
		return chooser.getSelectedFile();
	}
	private void fillMainCharacters()
	{
		String character=null;
		int index=-1;
		for(String e:lines)
		{
			if(e.indexOf(".")==-1)continue;
			character=e.substring(0,e.indexOf(".")).trim().toLowerCase();
			if(character.length()<avgSntncLen)
			{
				index=mainCharacters.indexOf(character);
				if(index==-1)
				{
					mainCharacters.add(character);
					appearance.add(1);
				}
				else
				appearance.set(index,appearance.get(index)+1);
		
			}
			else continue;
		}
		filterMainCharacters();
	}
	private boolean isCharacter(String character)
	{
		if(character.equalsIgnoreCase("all") || character.contains("scene"))
			return false;
		char[] charArr=character.toCharArray();
		for(char ch:charArr)
			if(!Character.isLetter(ch))
				return false;
		return true;
	}
	private void filterMainCharacters()
	{
		for(int i=0,n=mainCharacters.size();i<n;i++)
		{
			if(appearance.get(i)<=3 || !isCharacter(mainCharacters.get(i)))
			{
				mainCharacters.remove(i);
				appearance.remove(i--);
				n--;
			}
			else	
				continue;
		}
	}
}