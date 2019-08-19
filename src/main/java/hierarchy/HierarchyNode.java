package hierarchy;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;


public class HierarchyNode {
        private final String topLevel;
        private final String secondLevel;
        private final String thirdLevel;
        private final String fourthLevel;

        public String getTopLevel() {
            return topLevel;
        }

        public String getSecondLevel() {
            return secondLevel;
        }

        public String getThirdLevel() {
            return thirdLevel;
        }

        public String getFourthLevel() {
            return fourthLevel;
        }

        public HierarchyNode(String topLevel, String secondLevel, String thirdLevel, String fourthLevel) {
            this.topLevel = topLevel;
            this.secondLevel = secondLevel;
            this.thirdLevel = thirdLevel;
            this.fourthLevel = fourthLevel;
        }


        @Override
        public String toString() {
            return "HierarchyNode{" +
                "topLevel='" + topLevel + '\'' +
                ", secondLevel='" + secondLevel + '\'' +
                ", thirdLevel='" + thirdLevel + '\'' +
                ", fourthLevel='" + fourthLevel + '\'' +
                '}';
        }

        public static void main(String[] args) {
            NodeRepository nodeRepository = new NodeRepository();
            System.out.println(flat(nodeRepository.findBySecondLevel("secondLevel1")));
            System.out.println(flat(nodeRepository.findByThirdLevel("thirdLevel2")));
            System.out.println(flat(nodeRepository.topLevel()).entrySet());

        }

        private static Map<String, Map<String, List<String>>> flat(List<HierarchyNode> nodes) {
            return nodes.stream()
                        .collect(groupingBy(HierarchyNode::getSecondLevel,
                                            groupingBy(HierarchyNode::getThirdLevel,
                                                       mapping(HierarchyNode::getFourthLevel, toList()))));
        }


    private static class NodeRepository {

        List<HierarchyNode> hierarchyNodes = List.of(new HierarchyNode("topLevel", "secondLevel1", "thirdLevel1", "fourthLevel1"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel1", "fourthLevel2"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel2", "fourthLevel3"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel2", "fourthLevel4"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel2", "fourthLevel5"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel3", "fourthLevel7"),
                                                     new HierarchyNode("topLevel", "secondLevel1", "thirdLevel3", "fourthLevel6"),
                                                     new HierarchyNode("topLevel", "secondLevel2", "thirdLevel4", "fourthLevel8"));


        public List<HierarchyNode> findBySecondLevel(String secondLevel) {
            return hierarchyNodes.stream()
                                 .filter(e -> e.getSecondLevel().equals(secondLevel))
                                 .collect(toList());
        }

        public List<HierarchyNode> findByThirdLevel(String thirdLevel) {
            return hierarchyNodes.stream()
                                 .filter(e -> e.getThirdLevel().equals(thirdLevel))
                                 .collect(toList());
        }

        public List<HierarchyNode> topLevel() {
            return hierarchyNodes;
        }
    }


}


