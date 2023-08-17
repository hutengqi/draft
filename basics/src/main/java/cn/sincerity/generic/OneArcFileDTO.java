package cn.sincerity.generic;

/**
 * OneArcFileDTO
 *
 * @author Ht7_Sincerity
 * @date 2023/8/10
 */
public class OneArcFileDTO implements ArcFile{

    private String oneArcFileNo;

    @Override
    public String getArcFileNo() {
        return oneArcFileNo;
    }
}
