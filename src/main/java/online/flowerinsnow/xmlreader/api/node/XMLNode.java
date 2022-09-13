package online.flowerinsnow.xmlreader.api.node;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public interface XMLNode {
    /**
     * 获取当前节点的名称
     *
     * @return 当前节点的名称
     */
    @NotNull String getName();
    /**
     * 获取当前节点的数值
     *
     * @return 当前节点的数值
     */
    @NotNull String getValue();
    /**
     * 获取当前节点的byte数值
     *
     * @return 当前节点的byte数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    byte getValueAsByte() throws NumberFormatException;
    /**
     * 获取当前节点的short数值
     *
     * @return 当前节点的short数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    short getValueAsShort() throws NumberFormatException;
    /**
     * 获取当前节点的int数值
     *
     * @return 当前节点的int数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    int getValueAsInt() throws NumberFormatException;
    /**
     * 获取当前节点的long数值
     *
     * @return 当前节点的long数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    long getValueAsLong() throws NumberFormatException;
    /**
     * 获取当前节点的布尔数值
     *
     * @return 当前节点的布尔数值，内容非<code>true</code>即为false
     */
    boolean getValueAsBool();
    /**
     * 获取当前节点的float数值
     *
     * @return 当前节点的float数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    float getValueAsFloat() throws NumberFormatException;
    /**
     * 获取当前节点的double数值
     *
     * @return 当前节点的double数值
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    double getValueAsDouble() throws NumberFormatException;
    @NotNull XMLNodeType getType();

    @Nullable String getAttribute(@NotNull String key);
    @NotNull Map<String, @NotNull String> getAttributes();

    /**
     * 获取单个子节点，如果有多个只获取其中第一个
     * 
     * @param key 路径
     * @return 单个子节点，如果有多个只获取其中第一个，没有则返回null
     */
    @Nullable XMLNode getChildNode(@NotNull String key);
    /**
     * 获取所有子节点
     *
     * @return K=节点名 V=节点
     */
    @NotNull List<XMLNode> getChildNodes();

    /**
     * 获取单个子节点的byte数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的byte数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    byte getChildByte(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的short数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的short数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    short getChildShort(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的int数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的int数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    int getChildInt(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的long数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的long数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    long getChildLong(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的float数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的float数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    float getChildFloat(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的double数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的double数值，如果有多个只获取其中第一个，没有则返回null
     * @throws NumberFormatException 如果格式化时出错抛出
     */
    double getChildDouble(@NotNull String key) throws NumberFormatException;

    /**
     * 获取单个子节点的byte数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的byte数值，内容非<code>true</code>即为false；如果有多个只获取其中第一个，没有则返回null
     */
    boolean getChildBool(@NotNull String key);

    /**
     * 获取单个子节点的数值，如果有多个只获取其中第一个
     *
     * @param key 路径
     * @return 单个子节点的数值，如果有多个只获取其中第一个，没有则返回null
     */
    @Nullable String getChild(@NotNull String key);

    /**
     * 遍历所有子节点
     *
     * @param deep 是否遍历子节点的子节点
     * @param action 每次遍历到后执行的操作
     */
    void forEachAllChildNodes(boolean deep, Consumer<XMLNode> action);

    /**
     * 返回它有没有子节点
     *
     * @return 如果有子节点，返回<code>true</code>
     */
    boolean hasChildNodes();
}
