public class DynamicQ extends Q {
    DynamicQ() {
        super();
    }

    DynamicQ(int size) {
        super(size);
    }

    // add needed to be changed
    @Override
    public void add(int data) throws Exception {
        int maxSize = super.getMaxCapacity();
        int currSize = super.getSize();
        if (currSize == maxSize) {
            int[] tmp = new int[currSize];
            for (int i = 0; i < currSize; i++) {
                tmp[i] = super.remove();
            }

            int newSize = maxSize * 2;
            super.intialize(newSize);

            for (int el : tmp)
                super.add(el);
        }

        // add the given element
        super.add(data);

    }
}
