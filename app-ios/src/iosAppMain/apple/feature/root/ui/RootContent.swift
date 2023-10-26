import SwiftUI
import FeatureRoot

public struct RootContent: View {
    @ObservedObject
    private var routerStates: ObservableValue<RouterState<AnyObject, RootComponentContractChild>>

    public init(_ component: RootComponentContractComponent) {
        routerStates = ObservableValue(component.routerState)
    }

    public var body: some View {
        let child = routerStates.value.activeChild.instance

        switch child {
        case let splash as RootComponentContractChild.Splash:
            SplashContent(splash.component)

        case let home as RootComponentContractChild.Home:
            HomeContent(home.component)

        default: EmptyView()
        }
    }
}
