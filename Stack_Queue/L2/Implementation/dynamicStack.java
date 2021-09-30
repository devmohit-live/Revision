public class dynamicStack extends stack {
    /**
     * // * No of constructor here should be min as there was in parent to call
     * super
     */
    dynamicStack() {
        super();
    }

    dynamicStack(int size) {
        super(size);
    }

    @Override
    public void push(int el) throws Exception {
        if (super.getSize() == super.getMaxSize()) {
            int oldSize = super.getSize();
            int newSize = oldSize * 2;
            int[] tmp = new int[oldSize];
            for (int i = oldSize - 1; i >= 0; i--) {
                tmp[i] = super.pop();
            }
            super.intialize(newSize);

            for (int data : tmp)
                super.push(data);
        }
        super.push(el);
    }

}
