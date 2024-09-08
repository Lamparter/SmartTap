using System.Threading.Tasks;
using SmartTap.Sdk;

#if __ANDROID__
using Android.Nfc;
using Android.Nfc.CardEmulators;
using Android.OS;
#endif

#if __IOS__
using CoreNFC;
using Foundation;
#endif

namespace SmartTap.Services;

public class NfcEmulator : INfcEmulator
{
    public Task<bool> EmulateCardAsync(byte[] cardData)
    {
#if __ANDROID__
        return EmulateCardAsyncAndroid(cardData);
#elif __IOS__
        return EmulateCardAsynciOS(cardData);
#else
        return Task.FromResult(false);
#endif
    }

#if __ANDROID__
    private Task<bool> EmulateCardAsyncAndroid(byte[] cardData)
    {
        // Android HCE logic
        // TODO: implement a full HostApduService
        return Task.FromResult(true);
    }
#endif

#if __IOS__
    private Task<bool> EmulateCardAsynciOS(byte[] cardData)
    {
        // iOS HCE logic
        // TODO: implement a full NFCNdefReaderSession
        return Task.FromResult(true);
    }
#endif
}

