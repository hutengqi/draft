package cn.sincerity.reflect;

/**
 * GenericContainer
 *
 * @author Ht7_Sincerity
 * @date 2022/11/18
 */
public interface GenericContainer<T extends GenericParameter> {

    T process(T parameter);
}
