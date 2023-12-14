import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Wall implements Structure {
    private List<Block> blocks;

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return flattenBlocks(blocks).stream().filter(block -> block.getColor().equals(color)).findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return flattenBlocks(blocks).stream().filter(block -> block.getMaterial().equals(material)).toList();
    }

    @Override
    public int count() {
        return flattenBlocks(blocks).size();
    }

    private List<Block> flattenBlocks(List<Block> blocks) {
        List<Block> result = new ArrayList<>();
        if (blocks != null) {
            blocks.forEach(block -> {
                result.add(block);
                if (block instanceof CompositeBlock compositeBlock)
                    result.addAll(flattenBlocks(compositeBlock.getBlocks()));
            });
        }
        return result;
    }
}