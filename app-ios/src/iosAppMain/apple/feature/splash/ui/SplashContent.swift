import SwiftUI
import FeatureRoot

public struct SplashContent: View {
    private let component: SplashContractComponent

    public init(_ component: SplashContractComponent) {
        self.component = component
    }

    public var body: some View {
        SplashPage(onFinished: { component.onFinished() })
    }
}
