package com.joshua.webpage.java;

import java.util.Stack;

/**
 * Created by bmk on 17-8-29.
 */
public class Deque {

    public static void main(String[] args) {
        Deque deque = new Deque();
        deque.push(1);
        deque.push(2);
        deque.push(3);
        deque.push(3);
        deque.push(4);
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
        System.out.println(deque.pop());
    }

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        while (!stack2.empty()){
            stack1.push(stack2.pop());
        }
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()){
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
