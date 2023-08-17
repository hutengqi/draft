package cn.sincerity.generic;

/**
 * TwoArcFileDTO
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
public class TwoArcFileDTO implements ArcFile{

    private String twoArcFileNo;

    @Override
    public String getArcFileNo() {
        return twoArcFileNo;
    }
}
