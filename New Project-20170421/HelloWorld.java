public class HelloWorld
{
    public static Square[][] board = new Square[7][7];
    
    public static int[] data = 
        { 9,5,10,6,2,
          7,1,2,8,4,
          5,3,6,3,7,
          1,8,1,4,10,
          4,2,9,5,3
        };

    public static void printBoardTaken()
    {
        for(int y=0;y<7;y++)
        {
            for(int x=0;x<7;x++)
            {
                System.out.print(board[x][y].taken + " ");
            }
            System.out.print("\n");
        }
    }
    public static void printBoardValue()
    {
        for(int y=0;y<7;y++)
        {
            for(int x=0;x<7;x++)
            {
                System.out.print(board[x][y].value + " ");
            }
            System.out.print("\n");
        }
    }
    
    public static void init()
    {
        for(int y=0;y<7;y++)
        {
            for(int x=0;x<7;x++)
            {
                board[x][y]=new Square();
                board[x][y].taken = false;
                board[x][y].value = 0;
            }
        }
        
        int vCount = 0;
        for(int y=1;y<6;y++)
        {
            for(int x=1;x<6;x++)
            {
                board[x][y].value = data[vCount++];
            }
        }
        
        for(int n=0; n<7;n++)
        {
            board[n][0].taken = true;
            board[n][6].taken = true;
            board[0][n].taken = true;
            board[6][n].taken = true;
        }
        
        // kill the extra square
        board[1][1].taken=true;

    }
    
    public static Pair findFree()
    {
        Pair p = new Pair();
        for(int y=1;y<6;y++)
        {
            for(int x=1;x<6;x++)
            {
                if( ! board[x][y].taken )
                {
                    p.x=x;
                    p.y=y;
                    return p;
                }
            }
        }
        return null;
    }
    
    public static int totalGroup(Group g)
    {
        int t=0;
        int x = g.p[g.cur].x;
        int y = g.p[g.cur].y;
        for(int i=0;i<6;i++)
        {
            t+=board[x][y].value;
        }
        
        return t;
    }
    
    public static void getGroup(Group g)
    {
        Pair p = g.p[g.cur];
        
        System.out.println("cur=" +g.cur+ "; dir=" +p.d+ " (" +p.x+ "," +p.y+ ")" );
        
        //base case
        if(totalGroup(g)==30 && g.cur==5)
        {
            return;
        }
        
        p.d += 1;
        
        if(p.d == 5 || g.cur == 5)
        {
            p.x=0;
            p.y=0;
            p.d=0;
            g.cur-=1;
            if(g.cur==0)
            {
                return;
            }
            else
            {
                getGroup(g);
            }
        }
        
        if(p.d == 1)
        {
            if(board[p.x+1][p.y].taken == false)
            {
                g.cur+=1;
                g.p[g.cur].x=p.x+1;
                g.p[g.cur].y=p.y;
                g.p[g.cur].d = 0;
            }
        System.out.println("---> cur=" +g.cur+ "; dir=" +g.p[g.cur].d+ " (" +g.p[g.cur].x+ "," +g.p[g.cur].y+ ")" );
        }
        else if (p.d == 2)
        {
            if(board[p.x][p.y+1].taken == false)
            {
                g.cur+=1;
                g.p[g.cur].x=p.x;
                g.p[g.cur].y=p.y+1;
                g.p[g.cur].d = 0;
            }
        }
        else if (p.d == 3)
        {
            if(board[p.x-1][p.y].taken == false)
            {
                g.cur+=1;
                g.p[g.cur].x=p.x-1;
                g.p[g.cur].y=p.y;
                g.p[g.cur].d = 0;
            }
        }
        else if (p.d == 4)
        {
            if(board[p.x][p.y-1].taken == false)
            {
                g.cur+=1;
                g.p[g.cur].x = p.x;
                g.p[g.cur].y = p.y-1;
                g.p[g.cur].d = 0;
            }
        }
        
        getGroup(g);
        
    }
    
    public static void main(String []args)
    {
        init();
        
        printBoardTaken();
        printBoardValue();
        
        Group g = new Group();
        
        Pair p;
        p=findFree();
        System.out.println( "(" +p.x+ "," +p.y+ ")" );
        
        g = new Group();
        
        g.p[0].x=p.x;
        g.p[0].y=p.y;
        g.p[0].d=0;
        g.cur = 0;
        
        getGroup(g);
        
        g.dump();
     }
}
