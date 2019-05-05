/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbsecurityapp;

/**
 *
 * @author Dungs
 */
public class Mobat 
{
    
    //(Ri, Cj)’ = (Ri, Cj) – ((K3,i Mod K1) Mod K2,j)+ K2,j
    public static int encrypt(int val, int k1, int k2, int k3)
    {
       int newVal=val - ((k3 % k1)%k2) + k2;
       return newVal;
    }
    
    //(Ri, Cj) = (Ri, Cj)’ + ((K3,i Mod K1) Mod K2,j) - K2,j
    public static int decrypt(int newVal, int k1, int k2, int k3)
    {
        int val=newVal + ((k3 % k1) % k2) - k2;
        return val;
    }
    
    //E(x) = ((x -32+K2+95) mod 95) +32 … (3)
    public static char encryptNonumeric(int k2, char c)
    {
        int eX, x;
        x=AsciiConverter.CharToASCII(c);
        eX=((x - 32 + k2 +95) % 95) + 32;
        return AsciiConverter.ASCIIToChar(eX);
    }
    
    //D(E(x)) = ((x -32 - K2+95) mod 95) +32 … (4)
    public static char decryptNonumeric(int k2, char c)
    {
        int dEX, x;
        x=AsciiConverter.CharToASCII(c);
        dEX=((x - 32 - k2 +95) % 95) + 32;
        return AsciiConverter.ASCIIToChar(dEX);
    }
}
