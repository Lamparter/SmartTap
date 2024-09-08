namespace SmartTap.Sdk;

public interface INfcEmulator
    {
        Task<bool> EmulateCardAsync(byte[] cardData);
    }
