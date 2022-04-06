/******************************************************************************

                            Online C Compiler.
                Code, Compile, Run and Debug C program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <stdio.h>
#include <stdlib.h>

void checkWall(int* code,int up, int right, int down, int left)
{
    if(up)
        *code = (*code | 0b0001); // = 1
    
    if(right)
        *code = (*code | 0b0010); // = 2
    
    if(down)
        *code = (*code | 0b0100); // = 4
    
    if(left)
        *code = (*code | 0b1000); // = 8
    
}

void createMap()
{
    
}

void createPerimWall()
{
    
}

int main()
{
    int code = 0;
    
    //              u,r,d,l
    //checkWall(&code,1,1,1,0);
    int NUM_ROWS = 4;
    int NUM_COLS = 4;
    
    int** map;
    
    //Mallocs entire Rows X Cols grid
    (*map) = (int*) malloc(sizeof(int) * (NUM_ROWS * NUM_COLS));

    // for(int i = 1; i < NUM_ROWS; i++)
    // {
    //     map[i] = 
    // }
    
    
    printf("%d",code);

    return 0;
}
