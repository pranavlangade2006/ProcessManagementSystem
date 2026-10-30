package memory;

import java.util.ArrayList;
import java.util.List;

public class MemoryManager {

    public static class MemoryBlock {
        public int blockId;
        public int size;
        public String processId;
        public boolean allocated;

        public MemoryBlock(int blockId, int size) {
            this.blockId = blockId;
            this.size = size;
            this.processId = "";
            this.allocated = false;
        }
    }

    private final int totalMemory;
    private final List<MemoryBlock> blocks;

    public MemoryManager(int totalMemory) {
        this.totalMemory = totalMemory;
        this.blocks = new ArrayList<>();

        if (totalMemory <= 0) {
            throw new IllegalArgumentException(
                    "Total memory must be greater than 0."
            );
        }
    }

    public boolean allocate(
            String processId,
            int memoryRequired
    ) {

        if (processId == null || processId.trim().isEmpty()) {
            return false;
        }

        if (memoryRequired <= 0) {
            return false;
        }

        // Prevent duplicate process allocation
        for (MemoryBlock block : blocks) {
            if (block.allocated
                    && block.processId.equals(processId)) {
                return false;
            }
        }

        if (getFreeMemory() < memoryRequired) {
            return false;
        }

        int blockId = blocks.size() + 1;

        MemoryBlock newBlock =
                new MemoryBlock(
                        blockId,
                        memoryRequired
                );

        newBlock.processId = processId;
        newBlock.allocated = true;

        blocks.add(newBlock);

        return true;
    }

    public boolean release(String processId) {

        if (processId == null || processId.trim().isEmpty()) {
            return false;
        }

        for (MemoryBlock block : blocks) {

            if (block.allocated
                    && block.processId.equals(processId)) {

                block.processId = "";
                block.allocated = false;

                return true;
            }
        }

        return false;
    }

    public int getUsedMemory() {

        int usedMemory = 0;

        for (MemoryBlock block : blocks) {

            if (block.allocated) {
                usedMemory += block.size;
            }
        }

        return usedMemory;
    }

    public int getFreeMemory() {
        return totalMemory - getUsedMemory();
    }

    public double getMemoryUtilization() {

        if (totalMemory == 0) {
            return 0;
        }

        return (getUsedMemory() * 100.0)
                / totalMemory;
    }

    public int getTotalMemory() {
        return totalMemory;
    }

    public List<MemoryBlock> getBlocks() {
        return new ArrayList<>(blocks);
    }

    public void clearMemory() {

        for (MemoryBlock block : blocks) {

            block.processId = "";
            block.allocated = false;
        }
    }
}