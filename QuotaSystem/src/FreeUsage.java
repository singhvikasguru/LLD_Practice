public class FreeUsage implements Usage{
    int limit;
    @Override
    public int getLimit()
    {
        return limit;
    }
    @Override
    public void setLimit(int limit)
    {
        this.limit=limit;
    }
}
