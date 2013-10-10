/**
 * Created with IntelliJ IDEA.
 * User: stallworthpaul
 * Date: 9/24/13
 * Time: 11:39 AM
 */
public class DATA {

    protected String Status;
    protected String Name;
    protected String Symbol;
    protected double LastPrice;
    protected double Change;
    protected double ChangePercent;
    protected String Timestamp;
    protected double MarketCap;
    protected long Volume;
    protected double ChangeYTD;
    protected double ChangePercentYTD;
    protected double High;
    protected double Low;
    protected double Open;

    @Override
    public String toString() {
        return this.Symbol + " : " + this.LastPrice;
    }

    DATA() { }
}
