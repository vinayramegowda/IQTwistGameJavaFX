.
Reviewer: Vinay (u6776177)
Author: Llewelyn (u6673089)

Review Comments:

switch (placement.substring(0,1)+placement.substring(3)){
            case "a0": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+102; positions[3] = m+10;  break;
            case "a1": positions[0] = m+101; positions[1] = m+9;   positions[2] = m+16;  positions[3] = m+117; break;
            case "a2": positions[0] = m;     positions[1] = m+108; positions[2] = m+9;   positions[3] = m+110; break;
            case "a3": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+8;   positions[3] = m+116; break;
            case "a4": positions[0] = m+2;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+110; break;
            case "a5": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+116; positions[3] = m+17;  break;
            case "a6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+102; positions[3] = m+8;   break;
            case "a7": positions[0] = m;     positions[1] = m+101; positions[2] = m+9;   positions[3] = m+117; break;

            case "b0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+109; positions[3] = m+10;  break;
            case "b1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+16;  break;
            case "b2": positions[0] = m;     positions[1] = m+101; positions[2] = m+9;   positions[3] = m+10;  break;
            case "b3": positions[0] = m+1;   positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  break;
            case "b4": positions[0] = m+101; positions[1] = m+2;   positions[2] = m+8;   positions[3] = m+9;   break;
            case "b5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+17;  break;
            case "b6": positions[0] = m+1;   positions[1] = m+2;   positions[2] = m+8;   positions[3] = m+109; break;
            case "b7": positions[0] = m;     positions[1] = m+108; positions[2] = m+9;   positions[3] = m+17;  break;

            case "c0": positions[0] = m;     positions[1] = m+101; positions[2] = m+2;   positions[3] = m+3;   break;
            case "c1": positions[0] = m;     positions[1] = m+108; positions[2] = m+16;  positions[3] = m+24;  break;
            case "c2": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+3;   break;
            case "c3": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; positions[3] = m+24;  break;
            case "c4": positions[0] = m;     positions[1] = m+101; positions[2] = m+2;   positions[3] = m+3;   break;
            case "c5": positions[0] = m;     positions[1] = m+108; positions[2] = m+16;  positions[3] = m+24;  break;
            case "c6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+3;   break;
            case "c7": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; positions[3] = m+24;  break;

            case "d0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+109; positions[4] = m+110; break;
            case "d1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+116; positions[4] = m+17;  break;
            case "d2": positions[0] = m+100; positions[1] = m+101; positions[2] = m+8;   positions[3] = m+9;   positions[4] = m+10;  break;
            case "d3": positions[0] = m;     positions[1] = m+101; positions[2] = m+8;   positions[3] = m+109; positions[4] = m+16;  break;
            case "d4": positions[0] = m+101; positions[1] = m+102; positions[2] = m+8;   positions[3] = m+9;   positions[4] = m+10;  break;
            case "d5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  positions[4] = m+117; break;
            case "d6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+108; positions[4] = m+109; break;
            case "d7": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+108; positions[3] = m+9;   positions[4] = m+17;  break;

            case "e0": positions[0] = m;     positions[1] = m+101; positions[2] = m+109; break;
            case "e1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+109; break;
            case "e2": positions[0] = m+100; positions[1] = m+108; positions[2] = m+9;   break;
            case "e3": positions[0] = m+100; positions[1] = m+101; positions[2] = m+8;   break;
            case "e4": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+109; break;
            case "e5": positions[0] = m;     positions[1] = m+108; positions[2] = m+109; break;
            case "e6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+108; break;
            case "e7": positions[0] = m+100; positions[1] = m+101; positions[2] = m+9;   break;

            case "f0": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; positions[3] = m+109; break;
            case "f1": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+117; break;
            case "f2": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  break;
            case "f3": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+109; positions[3] = m+16;  break;
            case "f4": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; break;
            case "f5": positions[0] = m;     positions[1] = m+8;   positions[2] = m+109; positions[3] = m+116; break;
            case "f6": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   positions[3] = m+109; break;
            case "f7": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+17;  break;

            case "g0": positions[0] = m+100; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  positions[4] = m+117; break;
            case "g1": positions[0] = m+101; positions[1] = m+102; positions[2] = m+108; positions[3] = m+9;   positions[4] = m+17;  break;
            case "g2": positions[0] = m+101; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; positions[4] = m+118; break;
            case "g3": positions[0] = m+1;   positions[1] = m+9;   positions[2] = m+110; positions[3] = m+116; positions[4] = m+117; break;
            case "g4": positions[0] = m+101; positions[1] = m+108; positions[2] = m+9;   positions[3] = m+10;  positions[4] = m+116; break;
            case "g5": positions[0] = m+100; positions[1] = m+101; positions[2] = m+9;   positions[3] = m+110; positions[4] = m+17;  break;
            case "g6": positions[0] = m+102; positions[1] = m+8;   positions[2] = m+9;   positions[3] = m+110; positions[4] = m+117; break;
            case "g7": positions[0] = m+1;   positions[1] = m+108; positions[2] = m+9;   positions[3] = m+117; positions[4] = m+118; break;

            case "h0": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   break;
            case "h1": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+16;  break;
            case "h2": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; break;
            case "h3": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; break;
            case "h4": positions[0] = m+100; positions[1] = m+1;   positions[2] = m+2;   break;
            case "h5": positions[0] = m+100; positions[1] = m+8;   positions[2] = m+16;  break;
            case "h6": positions[0] = m;     positions[1] = m+1;   positions[2] = m+102; break;
            case "h7": positions[0] = m;     positions[1] = m+8;   positions[2] = m+116; break;

            case "i0": positions[0] = m+1000; break; case "j0": positions[0] = m+1000; break; case "k0": positions[0] = m+1000; break; case "l0": positions[0] = m+1000; break;
        }
        return positions;
    }

There are a things likr above which need to be done in a more efficient way rather that making switch cases like this and which i think is very lengthy and not readable.Other than this i think all the variable names and everything is up 
to the mark.Good job by him .
