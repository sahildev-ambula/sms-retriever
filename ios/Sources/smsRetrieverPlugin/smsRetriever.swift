import Foundation

@objc public class smsRetriever: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
