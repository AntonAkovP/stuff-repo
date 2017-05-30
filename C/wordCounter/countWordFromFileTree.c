#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

int sizeV = 0;
int freedNodes = 0;

typedef struct node{
    bool wordSet;
    struct node *nextp[26];
} node;

void clear(char *toClear)
{
    char temp[32];
    int pT=0, pC=0;
    while(pC<32)
    {
        if((toClear[pC]>=65&&toClear[pC]<=90)||(toClear[pC]>=97&&toClear[pC]<=122))
        {
            if(toClear[pC]>=97)
                temp[pT]=toClear[pC]-32;
            else
                temp[pT]=toClear[pC];

            pT++;
        }

        pC++;
        if(toClear[pC]=='\0')pC=32;
    }
    temp[pT]='\0';
    //printf("temp: %s\n", temp);
    memset(toClear, 0, 32*sizeof(char));
    pC=0;
    while(pC<pT)
        toClear[pC]=temp[pC++];
}

int strCmp(const char* str1, const char* str2)
{
    if(!str1&&!str2)return 0;
    if(!str1)return -1;
    if(!str2)return 1;
    int i = 0;
    while(!(str1[i]=='\0'&&str2[i]=='\0'))
    {
        if(str1[i]=='\0')return -i-1;
        if(str2[i]=='\0')return i+1;

        if(str1[i]<str2[i]&&str1[i]+32!=str2[i])return -(i+5);
        if(str1[i]>str2[i]&&str1[i]-32!=str2[i])return i+5;

        i++;
    }
    return 0;
}

node * add(const node* head, const char* str)
{
    node *temp;//, *prev, *next;

    //printf("tries to add %s\n", str);
    //prev = NULL;
    //next = head;
    node *cur = head;

    int i=0;

    while(i<128)
    {
        if(str[i]=='\0')break;
        //printf("on character %d\n", str[i]);
        if(cur->nextp[str[i]-65])
        {
            //printf("doesn't make new node\n");
        }
        else{
            //printf("tries to malloc\n");
            temp = (node*)malloc(sizeof(node));
            //printf("passes memaloc\n");
            temp->wordSet=false;
            int k=0;
            while(k<26)
            {
                temp->nextp[k]=NULL;
                k++;
            }

            cur->nextp[str[i]-65]=temp;
        }

        cur = cur->nextp[str[i]-65];
        i++;

    }

    if(!cur->wordSet){
            cur->wordSet = true;
            sizeV++;
            //printf("adds word\n");
    }
    //else printf("str: %s\t\n", str); test for dupe words
    //printf("word exists\n\n");

    return head;
}


void free_tree(node *head) {
    node *prev = head;
    int i =0;
    while(i<25)
    {
        if(prev->nextp[i])free_tree(prev->nextp[i]);
        i++;
    }
    free(prev);
    freedNodes++;
}


int main(){
    char str[32];
    node *head, *p;
    head = (node*)malloc(sizeof(node));
    head->wordSet=false;
    int k=0;
    while(k<26)
    {
        head->nextp[k]=NULL;
        k++;
    }




    FILE *fp;
    char filepath[255];
    printf("File(use \\\\ and .txt): ");
    scanf("%s", &filepath);

    fp = fopen(filepath, "r");

    int testCNT=0;
    while (fscanf(fp, "%s", &str) != EOF) {
        if(feof(fp))break;
        clear(str);
        head=add(head,str);
        testCNT++;
    }

    fclose(fp);
    p = head;

    printf("\nUnique words: %d\n", sizeV);

    printf("Reads: %d\n", testCNT);

    free_tree(head);
    printf("freed nodes: %d\n\n", freedNodes);

    printf("Runs a bit faster if you comment out freedNodes++ and testCNT++\n");
    return 0;
}



