package util;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.lang.String;

public class XML {

    /**
     * Initial variables
     */
    public static final String sValue = "value";

    /**
     * <p>Searches in a nested hierarchy for a value that is defined by the given attribute name.</p>
     *
     * @param nodeList A full list with the affected region.
     * @param attributeName The name of the result field.
     * @return The result as String
     */
    public static final java.lang.String searchHierarchyByAttribute(final NodeList nodeList, final java.lang.String attributeName){
        return searchHierarchyByAttributeAndValueName(nodeList, attributeName, sValue);
    }

    /**
     * <p>Searches in a nested hierarchy for a value that is defined by the given attribute name and value.</p>
     *
     * @param valueName The name of the value tag.
     * @param nodeList A full list with the affected region.
     * @param attributeName The name of the result field.
     * @return The result as String
     */
    public static final java.lang.String searchHierarchyByAttributeAndValueName(final NodeList nodeList, final java.lang.String attributeName, final java.lang.String valueName){
        // Iterate to first level
        for(int i = 0; i < nodeList.getLength(); i++){

            // Get i element of nodeList
            Node node = nodeList.item(i);

            // Check if node is a element node
            if(node.getNodeType()==Node.ELEMENT_NODE){

                // Get chils of node
                Element element = (Element) node;
                NodeList nameList = element.getChildNodes();

                // Iterate child elements
                for(int j = 0; j < nameList.getLength(); j++){

                    // Get child element
                    Node n = nameList.item(j);
                    if(n.getNodeType()==Node.ELEMENT_NODE){
                        Element name = (Element) n;

                        // Return the result
                        if(name.getTagName().equals(attributeName)) {
                            return name.getAttribute(valueName);
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * Searches in a nested hierarchy for a value by tag and Attribute.
     *
     * Example (Skip to method to see example)
     * <code>
     * <telecom>
     *     <system value="phone"/>                // <tagName tagAttribute="tagValue">
     *     <value value="030-1234567"/>           // <attributeName attribute="RESULT">
     *     <use value="work"/>
     * </telecom>
     *</code>
     *
     * @param node the node where the search should be carried out
     * @param tagName the name of the field tag that condition should be fulfilled
     * @param tagAttribute the attribute of the field tag that condition should be fulfilled
     * @param tagValue the content of the field that condition should be fulfilled
     * @param attributeName name of the result field
     * @param attribute attribute of the result field
     * @return result as String
     */
    public static final java.lang.String searchHierarchyByNodeAndTagAndAttribute(final Node node, final java.lang.String tagName, final java.lang.String tagAttribute, final java.lang.String tagValue, final java.lang.String attributeName, final java.lang.String attribute){
        // Initialize variable
        java.lang.String value = null;

        // Check if node is a element node
        if(node.getNodeType()==Node.ELEMENT_NODE){

            // Get children of node
            Element element = (Element) node;
            NodeList nameList = element.getChildNodes();

            // safe preCondition
            boolean preCondition = false;

            // Iterate child elements
            for(int j = 0; j < nameList.getLength(); j++){

                // Get child element
                Node n = nameList.item(j);
                if(n.getNodeType()==Node.ELEMENT_NODE){
                    Element name = (Element) n;
                    if(name.getTagName().equals(tagName) && name.getAttribute(tagAttribute).equals(tagValue)){
                        preCondition = true;
                    }

                    // Return if preCondition is true
                    if(name.getTagName().equals(attributeName) && preCondition) {
                        return name.getAttribute(attribute);
                    }
                }
            }
        }
        return value;
    }

    /**
     * Searches in a nested hierarchy NodeList for a value by tag and Attribute.
     *
     * Example (Skip to method to see example)
     * <telecom>
     *     <system value="phone"/>                // <tagName tagAttribute="tagValue">
     *     <value value="030-1234567"/>           // <attributeName attribute="RESULT">
     *     <use value="work"/>
     * </telecom>
     *
     * @param nodeList full list with the affected region
     * @param tagName the name of the field tag that condition should be fulfilled
     * @param tagAttribute the attribute of the field tag that condition should be fulfilled
     * @param tagValue the content of the field that condition should be fulfilled
     * @param attributeName name of the result field
     * @param attribute attribute of the result field
     * @return result as String
     */
    public static java.lang.String searchHierarchyByTagAndAttribute(NodeList nodeList, final java.lang.String tagName, final java.lang.String tagAttribute, final java.lang.String tagValue, final java.lang.String attributeName, final java.lang.String attribute){
        // Initialize variable
        String value = null;

        // Iterate to first level
        for(int i = 0; i < nodeList.getLength(); i++){
            // Get i element of nodeList
            value = searchHierarchyByNodeAndTagAndAttribute(nodeList.item(i), tagName, tagAttribute, tagValue, attributeName, attribute);

            // Break method if value not null
            if(value != null){
                return value;
            }
        }

        return value;
    }

}
