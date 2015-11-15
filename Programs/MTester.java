public class MTester 
{
	public static void main(String[] args)
	{
		MFolder root = MBuild.genList();
		print(root,0);
	}
	
	private static void print(MItem item, int level)
	{
		if(item.isFile())
		{
			for(int i = level; i > 0; i--)
				System.out.print("\t");
			System.out.println(item.path().substring(item.path().lastIndexOf('\\')));
		}
		else if(item.isFolder())
		{
			for(int i = level; i > 0; i--)
				System.out.print("\t");
			
			String[] splitPath = item.path.split("[\\\\]");
			System.out.println(splitPath[splitPath.length-2]);
			for(MItem itm: ((MFolder)item).contents())
				print(itm, level+1);
		}
	}
}
