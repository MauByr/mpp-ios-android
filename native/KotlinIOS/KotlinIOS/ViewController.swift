import UIKit
import SharedCode

class ViewController: UIViewController {

    @IBOutlet var fareSearchButton: UIButton!
    @IBOutlet var trainsTable: UITableView!

    @IBAction func buttonPressed(_ sender: Any) {
        presenter.onSearchClicked()
    }

    var validTrains : [Journey] = []

    private let presenter: ApplicationContractPresenter = ApplicationPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        presenter.onViewTaken(view: self)
        setupTable()
    }
}

extension ViewController: UITableViewDataSource, UITableViewDelegate{
    private func setupTable(){
        self.trainsTable.dataSource = self
        self.trainsTable.delegate = self
        self.registerTableViewCells()
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.validTrains.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "customTableViewCell") as? customTableViewCell {
            cell.setJourney(journey: validTrains[indexPath.row])
            return cell
        }
        
        return UITableViewCell()
    }
    private func registerTableViewCells() {let textFieldCell = UINib(nibName: "customTableViewCell", bundle: nil)
        self.trainsTable.register(textFieldCell, forCellReuseIdentifier: "customTableViewCell")
    }
    
    private func fillTable(){
        
    }
}


extension ViewController: ApplicationContractView {
    
    func showResults(result: FareResponse) {
        print("DATA")
        print(result.outboundJourneys.count)
        validTrains = result.outboundJourneys
        trainsTable.reloadData()
    }
    

    
    func populateStationList(stations: [String]) {
        // TODO: not yet implemented
    }
    
    func showAlert(msg: String) {
        // TODO: not yet implemented
    }
    
    func showResults(result: String) {
        // TODO: not yet implemented
    }
    
    func setLabel(text: String) {
        // TODO: not yet implemented
    }
}
