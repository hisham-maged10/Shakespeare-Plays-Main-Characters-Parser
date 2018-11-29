/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
public class TestMainCharacters
{
	public static void main(String[] args)
	{
		testing();
	}
	public static void testing()
	{
		MainCharactersPlay mc=new MainCharactersPlay();
		String[] characters=mc.getMainCharacters();
		Integer[] appearance=mc.getAppearance();
		System.out.println("List of characters and their appearance");
		for(int i=0;i<characters.length;i++)
		{
			System.out.println("Character: "+characters[i]+" appeared "+appearance[i]+" times.");
		}
		System.out.println();
		System.out.println("Characters from 10 to 15 speaking parts are");
		characters=mc.getCharactersWithNumParts(10,15);
		for(String e:characters)System.out.println(e);
		System.out.println();
		String[] max=mc.getMaxCharacter();
		System.out.println("Character that had the most parts is: "+max[0]+" and appeared "+max[1]+" times.");
		
	}
}