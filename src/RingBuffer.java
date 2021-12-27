package com.masondierkes;

import java.util.Arrays;

public class RingBuffer
{
    private double[] data;          // items in the buffer
    private int      first;         // index for the next dequeue or peek
    private int      last;          // index for the next enqueue
    private int      size;          // number of items in the buffer

    private int capacity;
    /** create an empty buffer, with given max capacity */
    public RingBuffer(int capacity) {
        this.capacity = capacity;
        data = new double[capacity];
        first = 0;
        last = 0;
    }

    /** return number of items currently in the buffer */
    public int size() {
        return size;
    }

    /** is the buffer empty (size equals zero)? */
    public boolean isEmpty() {
        return size == 0;
    }
    /** is the buffer full (size equals array capacity)? */
    public boolean isFull() {
        return size == capacity;
    }

    /** add item x to the end */
    public void enqueue(double x) {

        if(isFull())
            throw new RuntimeException("Attempted to enqueue to full buffer");


        data[last] = x;
        last++;
        if(last == data.length)
            last = 0;

        size++;
    }

    /** delete and return item from the front */
    public double dequeue() {

        Double output = data[first];
        size--;
        first++;

        if(first == data.length)
            first = 0;

        return output;
    }

    /** return (but do not delete) item from the front */
    public double peek() {
        return data[first];
    }

    @Override
    public String toString() {
        String output = "";
        for(Double num : data)
            output += (num + " ");
        return output;
    }

    /** a simple test of the constructor and methods in RingBuffer */
    public static void main(String[] args) {
        int N = 100;
        RingBuffer buffer = new RingBuffer(N);
        for (int i = 1; i <= N; i++) {
            buffer.enqueue(i);
            System.out.println(i);
            System.out.println(buffer);
        }
        double t = buffer.dequeue();
        buffer.enqueue(t);
        System.out.println("Size after wrap-around is " + buffer.size());
        while (buffer.size() >= 2) {
            double x = buffer.dequeue();
            double y = buffer.dequeue();
            buffer.enqueue(x + y);
        }
        System.out.println(buffer.peek());

        /*
         * Your program should produce the following output:
         *
         *  Size after wrap-around is 100
			*  5050.0
         */
    }
}
