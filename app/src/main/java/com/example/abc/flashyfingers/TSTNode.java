package com.example.abc.flashyfingers;

/**
 * Created by ABC on 01-Oct-16.
 */
public class TSTNode {

    char data;

    boolean isEnd;

    TSTNode left, middle, right;



    /** Constructor **/

    public TSTNode(char data)

    {

        this.data = data;

        this.isEnd = false;

        this.left = null;

        this.middle = null;

        this.right = null;

    }
}
