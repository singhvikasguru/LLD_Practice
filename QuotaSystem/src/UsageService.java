public class UsageService {
    Usage usageType;
    public int getUsageLimit()
    {
        return usageType.getLimit();
    }
    public void updateUsage(int limit)
    {
        usageType.setLimit(limit);
    }
}
