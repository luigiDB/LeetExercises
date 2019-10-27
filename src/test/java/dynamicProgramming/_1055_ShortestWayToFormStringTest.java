package dynamicProgramming;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class _1055_ShortestWayToFormStringTest {

    private _1055_ShortestWayToFormString shortestWayToFormString;

    @Before
    public void setUp() throws Exception {
        shortestWayToFormString = new _1055_ShortestWayToFormString();
    }

    @Test
    public void given1() {
        Assert.assertEquals(2, shortestWayToFormString.evaluate("abc", "abcbc"));
    }

    @Test
    public void given2() {
        Assert.assertEquals(-1, shortestWayToFormString.evaluate("abc", "acdbc"));
    }

    @Test
    public void given3() {
        Assert.assertEquals(3, shortestWayToFormString.evaluate("abc", "acbac"));
    }

    @Test
    public void extreme() {
        String source = "emjohvkpmowgvcelwvcsmuwcvabqpnywcssceruvmmdfdsyewzrwjxnytiityfbnekizhrxukhjkyydsnocxblhuvncxzdyrrkkfbrynuoalfhcdxgjlpbvfvehuokxszeggkghjcneefrfyntnwobavnaxkkqpdvvqunwzziwnfvrwntscrzrjzspkteyznrhgmnnbzxlreupkobfweoyuhfjtffrcvmoksxhhqlepwwtaazdsqeliyqbctkvjohfznwqzwcotpyfjwnbinakuhmrzgqdobdjhztaxiootarfetrnkkvcmhgnbavrfncccbcizgztijwnbxjpxfujbjmuquajjbrelxmdxtvlaxuqozojjaebkenjwagbgumwwjcsxvfngaflwvosgrpsexugtkmfgvvwphpdjffmcnwqxtffvtyvlgfdgtflndyhhhgulzxlklotricosryvnwqfifpsupddafvycqxdgagecgnntzavzjruoxrvistcsdjbmljzorrzhtljiphwwiouqdacooubazpqlbawjnbgjmaekytilyiygpnrugsvuvwoedixkwbydqpmmlrknohhstjudijqaxzfymlhjciufghixsuggcbtpzlbgnxarjcovnxyxjhwgnkdnotntnuoixvsxlxurigzcnwflrvyvxrlbufynirpahwkvffkksarqppoinmmmfziuibzqemjbbvxpsntrutestixcanksrchkprzbagileowbprnmayodccjxehcsmaolvakpdcgiuzkilluhvpnnewnietbyvgwnnjeuaxnscztnfdnqvxuemtxxpgrxhuyobepncaiiegiyqqfmmjfpudlodlvmadnpuevholkvfdaqxiehqqybldijnxksjzsjsvmsdvzsxrfkfhidvblbjxsiryypzimkzkpxaelopwrvvzevhnxatkxmnnuwvavhjhlxkiymintrwiaruitij";
        String dest = "wijzkueuvdokxksrrdcyqbonsbehcscocseybpfuojgcxfaejoybgmeeloxfodjqfcmxmwvtfbbbhtzzevfsjaqnegbjdjjcxwyefdjgxfnejvrbtgbvewbmbreioidiymurqfsidrplohldiqofqsbzmgcumrwsbnawaznpwifahqlwdbnyjljdhhfmsmbyvidnaedinaautzsbfthtkmbebyxlijwsavpfytplzruzpkzesvwhmipwrufowwopxxjsfkcspiqkdgbujlcpbwwnpvxglvdrmfxlfxwhkzajhohxqzfuyybradugmywibknlrfsmjobhhiqtlawqtrjzqvhwhdvwdqjhgccoscwlcdxpvwhgaklqpgufqsxamxooaqndkdlvlcnbrrdxrhukarpsqvqqtdogtpxojzpaxdhingqgkgfbdhicesvuexoikefkfhphapwjvjtwfmoftxgskvamdlrlcygmrfvzyrzzoqunjnjzkquqlpiublnafkxeprxjkiapminstxdnudvfwqkmqswcwugkcpjsjiqdmzmezouosgycxghopuofilfnfkvmcnzkardyzzfkvbpweogdbbhcjkbuwoiebegkcamnzrmbfkgiadgfsadszrlelppeatbjtjrftzkyglrgovuokncefrnoumcwayvzduobjvfuhzlhsjbbrkklmzziwlwgwzmutbzmskqtocfmtuckcizowdejmfezmurlilkvqpiueazwypzyofnzawblgaycgbguoulhuaozvoswttdotbmdyrviuwtolqqvxucjbuubjqssaeymbuhrpeofkvliuvqgbeeetqimnztfcwzwfpyzmctmhfovykhjxezanzupprtoeeylxwetxuypehgkxhqlwsctsxmlsgugcdudjbrueamezzndpyhcujgpuazmdfdulibqadwgmxhnmmbtzloolpnvbokwaqsvxegonmfgmyie";
        Assert.assertEquals(28, shortestWayToFormString.evaluate(source, dest));
    }
}