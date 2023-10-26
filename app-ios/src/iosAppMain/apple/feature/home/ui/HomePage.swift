import SwiftUI

public struct HomePage: View {

    var onFinished: () -> Void

    public init(onFinished: @escaping () -> Void) {
        self.onFinished = onFinished
    }

    public var body: some View {
       HomeView()
    }
}


struct HomeView: View {
    let items = ["Label", "Monday", "Tuesday", "Thursday", "Wednesday",]
    var body: some View {
        NavigationView {
            ScrollView{
                VStack {
                    DropdownSection(title: "Enabled", isDisabled: false, isError: false, items: items)
                    DropdownSection(title: "Disabled", isDisabled: true, isError: false, items: items)
                    DropdownSection(title: "Error", isDisabled: false, isError: true, items: items)
                    DropdownSection(title: "With Placeholder", isDisabled: false, isError: false, items: items)
                    DropdownSection(title: "Without Placeholder", isDisabled: false, isError: false, items: items)
                }
                .navigationTitle("Dropdown")
            }
        }
        .background()
    }
}

struct DropdownSection: View {
    @State private var selectedValue: String = "Item 1"
    @State private var isMenuExpanded: Bool = false
    let title: String
    let isDisabled: Bool
    let isError: Bool
    let items: [String]

    var body: some View {
        VStack {
            HStack{
                Text(title)
                    .font(.title)
                    .padding(.top, 10)
                Spacer()
            }
            .padding(.horizontal, 24)

            ZStack {
                Rectangle()
                    .fill(Color.gray)
                    .frame(maxWidth: .infinity)
                    .frame(height: 80)
                    .border(isError ? Color.red : Color.clear, width: 1) // Set border color based on 'isError'
                    .padding(8)

                HStack {
                    Text(selectedValue)
                    Spacer()
                    Button(action: {
                        if !isDisabled {
                            isMenuExpanded.toggle()
                        }
                    }) {
                        Image(systemName: "arrow.down")
                            .foregroundColor(.black)
                    }
                    .disabled(isDisabled)
                }
                .padding(.horizontal, 24)
            }

            if isMenuExpanded {
                HStack{
                    VStack(alignment: .leading){
                        ForEach(items, id: \.self) { item in
                            if(
                                item != "Label"){
                                Button(action: {
                                    selectedValue = item
                                    isMenuExpanded = false
                                }) {
                                    Text(item)
                                        .foregroundColor(.black)
                                }
                            }
                        }
                    }
                    Spacer()
                }
                .padding(
                    .horizontal, 24
                )
                .padding(
                    .vertical, 12
                )

            }
            HStack{

                Text(isError ? "Error Message" : "Helper Text")
                    .foregroundColor(isError ? .red : .gray)
                    .font(.system(size: isError ? 14 : 16))
                Spacer()
            }
            .padding(.horizontal, 24)
        }
    }
}
