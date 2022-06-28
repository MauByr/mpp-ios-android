import UIKit
import SharedCode

class ViewController: UIViewController {

    @IBOutlet private var label: UILabel!

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
    }
}

extension ViewController: ApplicationContractView {
    func populateStationList(stations: [String]) {
        <#code#>
    }
    
    func showAlert(msg: String) {
        <#code#>
    }
    
    func showResults(result: String) {
        <#code#>
    }
    
    func setLabel(text: String) {
        label.text = text
    }
}
