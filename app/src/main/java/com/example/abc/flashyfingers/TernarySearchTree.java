package com.example.abc.flashyfingers;

import java.util.ArrayList;

/**
 * Created by ABC on 01-Oct-16.
 */
public class TernarySearchTree {
    private TSTNode root;

    private ArrayList<String> al;



    /** Constructor **/

    public TernarySearchTree()

    {

        root = null;

    }

    /** function to check if empty **/

    public boolean isEmpty()

    {

        return root == null;

    }

    /** function to clear **/

    public void makeEmpty()

    {

        root = null;

    }

    /** function to insert for a word **/

    public void insert(String word)

    {

        root = insert(root, word.toCharArray(), 0);

    }

    /** function to insert for a word **/

    public TSTNode insert(TSTNode r, char[] word, int ptr)

    {

        if (r == null)

            r = new TSTNode(word[ptr]);



        if (word[ptr] < r.data)

            r.left = insert(r.left, word, ptr);

        else if (word[ptr] > r.data)

            r.right = insert(r.right, word, ptr);

        else

        {

            if (ptr + 1 < word.length)

                r.middle = insert(r.middle, word, ptr + 1);

            else

                r.isEnd = true;

        }

        return r;

    }



    /** function to delete a word **/







    /** function to search for a word **/

    public boolean search(String word)

    {

        return search(root, word.toCharArray(), 0);

    }

    /** function to search for a word **/

    private boolean search(TSTNode r, char[] word, int ptr)

    {

        if (r == null)

            return false;



        if (word[ptr] < r.data)

            return search(r.left, word, ptr);

        else if (word[ptr] > r.data)

            return search(r.right, word, ptr);

        else

        {

            if (r.isEnd && ptr == word.length - 1)

                return true;

            else if (ptr == word.length - 1)

                return false;

            else

                return search(r.middle, word, ptr + 1);

        }

    }

    /** function to print tree **/

    public String toString()

    {

        al = new ArrayList<String>();

        traverse(root, "");

        String s = al.get(0)+al.get(1)+al.get(2)+al.get(3);

        return s;

    }

    /** function to traverse tree **/

    private void traverse(TSTNode r, String str)

    {

        if (r != null)

        {

            traverse(r.left, str);



            str = str + r.data;

            if (r.isEnd)

                al.add(str);



            traverse(r.middle, str);

            str = str.substring(0, str.length() - 1);



            traverse(r.right, str);

        }

    }
}
