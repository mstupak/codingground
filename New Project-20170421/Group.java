public class Group
{
    public int cur;
    public Pair start;
    public Pair[] p;
    
    public Group()
    {
        start = new Pair();
        start.x = 1;
        start.y = 1;
        
        p = new Pair[6];
        
        for(int i=0;i<6;i++)
        {
            p[i] = new Pair();
            p[i].x = 0;
            p[i].y = 0;
            p[i].d = 0;
        }
    }
    
    public void dump()
    {
        System.out.println("================");
        for(int i=0;i<6;i++)
        {
            System.out.println( "(" +p[i].x+ "," +p[i].y+ ")" );
        }
    }
}
