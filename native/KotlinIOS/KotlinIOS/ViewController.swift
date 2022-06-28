import UIKit
import SharedCode

class ViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {

    @IBOutlet private var label: UILabel!
    @IBOutlet var trainsTable: UITableView!
    @IBOutlet var fareSearchButton: UIButton!
    //@IBAction func doSomething(){onSearchClicked()}
    
    let validTrains = ["1", "2", "3", "4"]

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        
        self.trainsTable.rowHeight = UITableView.automaticDimension
        
        self.trainsTable.dataSource = self
        self.trainsTable.delegate = self
        self.registerTableViewCells()
    }
}

extension ViewController: ApplicationContractView {
    
    func tableView(_ tableView: UITableView,
                   numberOfRowsInSection section: Int) -> Int {
        return self.validTrains.count
    }
    
    func tableView(_ tableView: UITableView,
                   cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "customTableViewCell") as? customTableViewCell {
            return cell
        }
        
        return UITableViewCell()
    }
    
    private func registerTableViewCells() {
        let textFieldCell = UINib(nibName: "customTableViewCell",
                                  bundle: nil)
        self.trainsTable.register(textFieldCell,
                                forCellReuseIdentifier: "customTableViewCell")
    }
    
    func populateStationList(stations: [String]) {
        
    }
    
    func showAlert(msg: String) {
        
    }
    
    func showResults(result: String) {
        
    }
    
    func setLabel(text: String) {
        //label.text = text
    }

}
