import Foundation

@objc public class BillingManager: NSObject {
    @objc public func echo(_ value: String) -> String {
        print(value)
        return value
    }
}
