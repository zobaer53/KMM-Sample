import SwiftUI
import FeatureRoot

public struct HomeContent: View {
    private let component: HomeContractComponent

    public init(_ component: HomeContractComponent) {
        self.component = component
    }

    public var body: some View {
        HomePage(onFinished: { component.onFinished() })
    }
}
