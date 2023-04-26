package CSCIFinal;

import java.util.ArrayList;

public class MazeSkeleton {

    private final int C; //constant width 15 to maintain square grid
    private final int MAZE_EDGE_X;
    private final int MAZE_EDGE_Y;
    private ArrayList<MazeBlock> mazeFramework;
    private VRectangle v1;
    private VRectangle v2;
    private VRectangle v3;
    private VRectangle v4;
    private VRectangle v5;
    private VRectangle v6;
    private VRectangle v7;
    private VRectangle v8;
    private VRectangle v9;
    private VRectangle v10;
    private VRectangle v11;
    private VRectangle v12;
    private VRectangle v13;
    private VRectangle v14;
    private VRectangle v15;
    private VRectangle v16;


    private HRectangle h1;
    private HRectangle h2;
    private HRectangle h3;
    private HRectangle h4;
    private HRectangle h5;
    private HRectangle h6;
    private HRectangle h7;
    private HRectangle h8;
    private HRectangle h9;
    private HRectangle h10;
    private HRectangle h11;
    private HRectangle h12;
    private HRectangle h13;
    private HRectangle h14;
    private HRectangle h15;
    private HRectangle h16;
    private HRectangle w1;
    private HRectangle w2;
    private HRectangle w3;
    private HRectangle w4;
    private HRectangle w5;
    private HRectangle w6;
    private HRectangle w7;
    private HRectangle w8;
    private HRectangle w9;
    private HRectangle w10;
    private HRectangle w11;
    private VRectangle w12;
    private HRectangle w13;
    private VRectangle w14;
    private HRectangle w15;
    private VRectangle w16;
    private VRectangle w17;
    private HRectangle w18;
    private VRectangle w19;
    private HRectangle w20;
    private VRectangle w21;
    private HRectangle w22;
    private VRectangle w23;
    private HRectangle w24;
    private VRectangle w25;
    private HRectangle w26;
    private VRectangle w27;
    private HRectangle w28;
    private VRectangle w29;
    private HRectangle w30;
    private VRectangle w31;
    private HRectangle w32;
    private VRectangle w33;
    private HRectangle w34;
    private VRectangle w35;
    private HRectangle w36;
    private VRectangle w37;
    private HRectangle w38;
    private VRectangle w39;
    private HRectangle w40;
    private VRectangle w41;
    private HRectangle w42;
    private HRectangle w43;
    private HRectangle w44;

    public MazeSkeleton(int w) {
        mazeFramework = new ArrayList<>();
        C = 15;
        MAZE_EDGE_X = (w-(25*C))/2;
        MAZE_EDGE_Y = 108;

        //vertical lines
        v1 = new VRectangle(MAZE_EDGE_X,MAZE_EDGE_Y,C*14);
        v2 = new VRectangle(MAZE_EDGE_X+C*10,MAZE_EDGE_Y,C*4);
        v3 = new VRectangle(MAZE_EDGE_X+2*C,MAZE_EDGE_Y+(3*C),C*8);
        v4 = new VRectangle(MAZE_EDGE_X+6*C,MAZE_EDGE_Y+(10*C),C*4);

        v5 = new VRectangle(MAZE_EDGE_X+C*24,MAZE_EDGE_Y,C*14);
        v6 = new VRectangle(MAZE_EDGE_X+C*14,MAZE_EDGE_Y,C*4);
        v7 = new VRectangle(MAZE_EDGE_X+22*C,MAZE_EDGE_Y+(3*C),C*8);
        v8 = new VRectangle(MAZE_EDGE_X+18*C,MAZE_EDGE_Y+(10*C),C*4);

        v9 = new VRectangle(MAZE_EDGE_X,MAZE_EDGE_Y+(C*15),C*15);
        v10 = new VRectangle(MAZE_EDGE_X+C*10,MAZE_EDGE_Y+C*26,C*4);
        v11 = new VRectangle(MAZE_EDGE_X+2*C,MAZE_EDGE_Y+(17*C),C*10);
        v12 = new VRectangle(MAZE_EDGE_X+6*C,MAZE_EDGE_Y+(15*C),C*3);

        v13 = new VRectangle(MAZE_EDGE_X+C*24,MAZE_EDGE_Y+(C*15),C*15);
        v14 = new VRectangle(MAZE_EDGE_X+C*14,MAZE_EDGE_Y+C*26,C*4);
        v15 = new VRectangle(MAZE_EDGE_X+22*C,MAZE_EDGE_Y+(17*C),C*10);
        v16 = new VRectangle(MAZE_EDGE_X+18*C,MAZE_EDGE_Y+(15*C),C*3);

        //horizontal lines
        h1 = new HRectangle(MAZE_EDGE_X+C,MAZE_EDGE_Y,C*9);
        h2 = new HRectangle(MAZE_EDGE_X+3*C,MAZE_EDGE_Y+(3*C),C*7);
        h3 = new HRectangle(MAZE_EDGE_X+3*C,MAZE_EDGE_Y+(10*C),C*3);
        h4 = new HRectangle(MAZE_EDGE_X+C,MAZE_EDGE_Y+(13*C),C*5);

        h5 = new HRectangle(MAZE_EDGE_X+15*C,MAZE_EDGE_Y,C*9);
        h6 = new HRectangle(MAZE_EDGE_X+15*C,MAZE_EDGE_Y+(3*C),C*7);
        h7 = new HRectangle(MAZE_EDGE_X+19*C,MAZE_EDGE_Y+(10*C),C*3);
        h8 = new HRectangle(MAZE_EDGE_X+19*C,MAZE_EDGE_Y+(13*C),C*5);

        h9 = new HRectangle(MAZE_EDGE_X+C,MAZE_EDGE_Y+(29*C),C*9);
        h10 = new HRectangle(MAZE_EDGE_X+3*C,MAZE_EDGE_Y+(26*C),C*7);
        h11 = new HRectangle(MAZE_EDGE_X+3*C,MAZE_EDGE_Y+(17*C),C*3);
        h12 = new HRectangle(MAZE_EDGE_X+C,MAZE_EDGE_Y+(15*C),C*5);

        h13 = new HRectangle(MAZE_EDGE_X+15*C,MAZE_EDGE_Y+(29*C),C*9);
        h14 = new HRectangle(MAZE_EDGE_X+15*C,MAZE_EDGE_Y+(26*C),C*7);
        h15 = new HRectangle(MAZE_EDGE_X+19*C,MAZE_EDGE_Y+(17*C),C*3);
        h16 = new HRectangle(MAZE_EDGE_X+19*C,MAZE_EDGE_Y+(15*C),C*5);

        //INSIDES OF MAZE, w ---> walls
        w1 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(5*C),2*C);
        w2 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(6*C),2*C);
        w3 = new HRectangle(MAZE_EDGE_X + (7*C),MAZE_EDGE_Y+(5*C),4*C);
        w4 = new HRectangle(MAZE_EDGE_X + (7*C),MAZE_EDGE_Y+(6*C),4*C);

        w5 = new HRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(5*C),4*C);
        w6 = new HRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(6*C),4*C);
        w7 = new HRectangle(MAZE_EDGE_X + (19*C),MAZE_EDGE_Y+(5*C),2*C);
        w8 = new HRectangle(MAZE_EDGE_X + (19*C),MAZE_EDGE_Y+(6*C),2*C);

        w9 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(8*C),3*C);
        w10 = new HRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(8*C),5*C);
        w11 = new HRectangle(MAZE_EDGE_X + (18*C),MAZE_EDGE_Y+(8*C),3*C);

        w12 = new VRectangle(MAZE_EDGE_X + (8*C),MAZE_EDGE_Y+(8*C),6*C);
        w13 = new HRectangle(MAZE_EDGE_X + (9*C),MAZE_EDGE_Y+(10*C),2*C);
        w14 = new VRectangle(MAZE_EDGE_X + (16*C),MAZE_EDGE_Y+(8*C),6*C);
        w15 = new HRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(10*C),2*C);

        w16 = new VRectangle(MAZE_EDGE_X + (8*C),MAZE_EDGE_Y+(15*C),3*C);
        w17 = new VRectangle(MAZE_EDGE_X + (16*C),MAZE_EDGE_Y+(15*C),3*C);

        w18 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(19*C),3*C);
        w19 = new VRectangle(MAZE_EDGE_X + (5.5*C),MAZE_EDGE_Y+(19*C),4*C);
        w20 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(20*C),3*C);
        w21 = new VRectangle(MAZE_EDGE_X + (6*C),MAZE_EDGE_Y+(19*C),4*C);

        w22 = new HRectangle(MAZE_EDGE_X + (8*C),MAZE_EDGE_Y+(19*C),3*C);
        w23 = new VRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(19*C),2*C);

        w24 = new HRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(19*C),3*C);
        w25 = new VRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(19*C),2*C);

        w26 = new HRectangle(MAZE_EDGE_X + (18*C),MAZE_EDGE_Y+(19*C),3*C);
        w27 = new VRectangle(MAZE_EDGE_X + (18*C),MAZE_EDGE_Y+(19*C),4*C);
        w28 = new HRectangle(MAZE_EDGE_X + (18*C),MAZE_EDGE_Y+(20*C),3*C);
        w29 = new VRectangle(MAZE_EDGE_X + (19*C),MAZE_EDGE_Y+(19*C),4*C);

        w30 = new HRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(17*C),5*C);
        w31 = new VRectangle(MAZE_EDGE_X + (12*C),MAZE_EDGE_Y+(17*C),4*C);

        w32 = new HRectangle(MAZE_EDGE_X + (4*C),MAZE_EDGE_Y+(24*C),7*C);
        w33 = new VRectangle(MAZE_EDGE_X + (8*C),MAZE_EDGE_Y+(21*C),4*C);

        w34 = new HRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(24*C),7*C);
        w35 = new VRectangle(MAZE_EDGE_X + (16*C),MAZE_EDGE_Y+(21*C),4*C);

        w36 = new HRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(22*C),5*C);
        w37 = new VRectangle(MAZE_EDGE_X + (12*C),MAZE_EDGE_Y+(22*C),3*C);

        // center box
        w38 = new HRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(12*C),2*C);
        w39 = new VRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(12*C),4*C);

        w40 = new HRectangle(MAZE_EDGE_X + (13*C),MAZE_EDGE_Y+(12*C),2*C);
        w41 = new VRectangle(MAZE_EDGE_X + (14*C),MAZE_EDGE_Y+(12*C),4*C);

        w42 = new HRectangle(MAZE_EDGE_X + (10*C),MAZE_EDGE_Y+(15*C),5*C);

        w43 = new HRectangle(MAZE_EDGE_X + (2*C),MAZE_EDGE_Y+(22*C),2*C);

        w44 = new HRectangle(MAZE_EDGE_X + (21*C),MAZE_EDGE_Y+(22*C),2*C);

        mazeFramework.add(v1);
        mazeFramework.add(v2);
        mazeFramework.add(v3);
        mazeFramework.add(v4);
        mazeFramework.add(v5);
        mazeFramework.add(v6);
        mazeFramework.add(v7);
        mazeFramework.add(v8);
        mazeFramework.add(v9);
        mazeFramework.add(v10);
        mazeFramework.add(v11);
        mazeFramework.add(v12);
        mazeFramework.add(v13);
        mazeFramework.add(v14);
        mazeFramework.add(v15);
        mazeFramework.add(v16);

        mazeFramework.add(h1);
        mazeFramework.add(h2);
        mazeFramework.add(h3);
        mazeFramework.add(h4);
        mazeFramework.add(h5);
        mazeFramework.add(h6);
        mazeFramework.add(h7);
        mazeFramework.add(h8);
        mazeFramework.add(h9);
        mazeFramework.add(h10);
        mazeFramework.add(h11);
        mazeFramework.add(h12);
        mazeFramework.add(h13);
        mazeFramework.add(h14);
        mazeFramework.add(h15);
        mazeFramework.add(h16);

        mazeFramework.add(w1);
        mazeFramework.add(w2);
        mazeFramework.add(w3);
        mazeFramework.add(w4);
        mazeFramework.add(w5);
        mazeFramework.add(w6);
        mazeFramework.add(w7);
        mazeFramework.add(w8);
        mazeFramework.add(w9);
        mazeFramework.add(w10);
        mazeFramework.add(w11);
        mazeFramework.add(w12);
        mazeFramework.add(w13);
        mazeFramework.add(w14);
        mazeFramework.add(w15);
        mazeFramework.add(w16);
        mazeFramework.add(w17);

        mazeFramework.add(w18);
        mazeFramework.add(w19);
        mazeFramework.add(w20);
        mazeFramework.add(w21);
        mazeFramework.add(w22);
        mazeFramework.add(w23);
        mazeFramework.add(w24);
        mazeFramework.add(w25);
        mazeFramework.add(w26);
        mazeFramework.add(w27);
        mazeFramework.add(w28);
        mazeFramework.add(w29);
        mazeFramework.add(w30);
        mazeFramework.add(w31);
        mazeFramework.add(w32);
        mazeFramework.add(w33);
        mazeFramework.add(w34);
        mazeFramework.add(w35);
        mazeFramework.add(w36);
        mazeFramework.add(w37);

        mazeFramework.add(w38);
        mazeFramework.add(w39);
        mazeFramework.add(w40);
        mazeFramework.add(w41);
        mazeFramework.add(w42);

        mazeFramework.add(w43);
        mazeFramework.add(w44);

    }

    public ArrayList buildMaze(){
        return mazeFramework;
    }


}

