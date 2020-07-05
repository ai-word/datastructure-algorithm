package com.mcx.queue.round;

/**
 * 队列
 * 队列是一种特殊的线性表，特殊之处在于它只允许在表的前端进行删除操作，而在表的后端进行插入操作
 * 所以队列又称为先进先出（FIFO—first in first out）
 *
 * 循环队列
 * 在实际使用队列时，为了使队列空间能重复使用，往往对队列的使用方法稍加改进：无论插入或删除，
 * 一旦 rear 指针增 1 或 front 指针增 1 时超出了所分配的队列空间，就让它指向这片连续空间的起始位置
 * 实际上是把队列空间想象成一个环形空间，环形空间中的存储单元循环使用，用这种方法管理的队列也就称为循环队列
 *
 * 数组实现循环队列
 */
public class Main {

    public static class IntQueue {
        private final int[] array; // 存放队列元素的数组
        private final int maxSize; // 最大队列元素大小
        private int front; // 队头指针
        private int rear; // 队尾指针

        public IntQueue(int size) {
            this.array = new int[size];
            this.maxSize = size;
        }

        /**
         * 放入队列元素
         */
        public boolean put(int elem) {
            if (this.isFull()) {
                System.err.println("queue is full");
                return false;
            }
            // 把元素放入队尾
            this.array[this.rear] = elem;
            // 循环累加，当 rear + 1 等于 maxSize 时变成 0，重新累加
            this.rear = (this.rear + 1) % this.maxSize;
            return true;
        }

        /**
         * 取出队列元素
         */
        public int take() {
            if (this.isEmpty()) {
                System.err.println("queue is empty");
                return Integer.MIN_VALUE;
            }
            // 取出当前队头指向的元素
            int elem = this.array[this.front];
            this.front = (this.front + 1) % this.maxSize;
            return elem;
        }

        public boolean isEmpty() {
            return this.front == this.rear;
        }

        public boolean isFull() {
            // 空出一个位置，判断是否等于队头指针
            // 队尾指针指向的位置不能存放队列元素，实际上会比 maxSize 指定的大小少一
            return (this.rear + 1) % this.maxSize == this.front;
        }

        public int size() {
            return (this.rear + this.maxSize - this.front) % this.maxSize;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            int tempFront = this.front;
            for (int i = 0; i < this.size(); i++) {
                sb.append(this.array[tempFront]).append(" ");
                tempFront = (tempFront + 1) % this.maxSize;
            }
            sb.append("]");
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        IntQueue intQueue = new IntQueue(5);
        intQueue.put(1);
        intQueue.put(2);
        intQueue.put(3);
        intQueue.put(4);
        intQueue.put(5); // 队列已满，无法放入数据，实际上只能放 4 个元素

        System.out.println("intQueue:" + intQueue);

        int num = intQueue.take();
        System.out.println("取出一个元素:" + num);
        num = intQueue.take();
        System.out.println("取出一个元素:" + num);
        num = intQueue.take();
        System.out.println("取出一个元素:" + num);
        num = intQueue.take();
        System.out.println("取出一个元素:" + num);
        num = intQueue.take();
        System.out.println("取出一个元素:" + num);
        if (num == Integer.MIN_VALUE) {
            System.out.println("出队失败!!!");
        }

        // 取出数据后可以继续放入数据
        intQueue.put(5);
        System.out.println("intQueue:" + intQueue);
    }
}
